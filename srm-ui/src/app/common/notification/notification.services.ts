import {Injectable} from "@angular/core";
import {Subject} from "rxjs/Subject";
import {Notification} from './notification.model';

@Injectable()
export class NotificationService{
     private _notifications=new Subject<Notification>();
     public notesAdded=this._notifications.asObservable();
     public add(notification:Notification){
         this._notifications.next(notification);
     }
}