import {Injectable} from '@angular/core';
import { Http, Headers, RequestOptions, Response } from "@angular/http";
import { Subject } from 'rxjs/Subject';
import { Observable } from 'rxjs/Rx';
import {RequestDto} from '../../dto/dto.component';
import {NotificationService} from '../../common/notification/notification.services';
import {Notification} from '../../common/notification/notification.model';
@Injectable()
export class ApiServices{
  private HTTP_POST_OPER = "post";
  private HTTP_GET_OPER = "get";
  private PARAM_TYPE_QUERY = "query";
  private PARAM_TYPE_HEADER = "header";
  public static ENV_TYPE=null;
  private CONTEXT_PATH="http://localhost/srm";
  constructor( private _http: Http,private _noteService:NotificationService) { }
  public execute(obj:RequestDto): Observable<any> {
      console.info(obj);
      if(obj.method.toLowerCase() == this.HTTP_POST_OPER ) {
            console.info("Executed");
            let options = this.setParamHeaders(obj,obj.model);
            obj.serviceUrl=ApiServices.ENV_TYPE=='local'?(this.CONTEXT_PATH+obj.serviceUrl):obj.serviceUrl;
            return this._http.post(obj.serviceUrl,obj.model,options).map(( res: Response ) => res.json() ).catch( this.handleError );
      }else if ( obj.method.toLowerCase() == this.HTTP_GET_OPER ) {
            let paramQuery = {};
            if ( obj.paramType != null && obj.paramType == this.PARAM_TYPE_QUERY && obj.paramValue ) {
                paramQuery[obj.paramValue] =obj.model;
            } else {
                paramQuery =obj.model;
            }
            return this._http.get( obj.serviceUrl, paramQuery ).map( res => res.json() ).catch( this.handleError );
     }
   }
   private handleError( error: any ) {
        let errMsg = ( error.message ) ? error.message :
            error.status ?(error.statusText=='error'||error.status==404||error.status==401||error.status==302)?'Current Session Expired, Please re-login.'
            :`${error.statusText}`:'Server error';
        console.error( errMsg );
        return Observable.throw( errMsg );
    }
    private setParamHeaders( obj: any, model: any ): any {
        let headerparam = {}
        headerparam['Content-Type'] = 'application/json';
        if ( obj != null ) {
            if ( obj.authorization != null ) {
                headerparam['Authorization'] = obj.authorization;
            }
            if ( obj.paramType != null && obj.paramType == this.PARAM_TYPE_HEADER ) {
                headerparam[obj.paramValue] == model;
            }
        }
        let headers = new Headers( headerparam )
        let options = new RequestOptions( { 'headers': headers });
        return options;
   }
   public pushNotification(notification:Notification){
       this._noteService.add(notification);
   }
}