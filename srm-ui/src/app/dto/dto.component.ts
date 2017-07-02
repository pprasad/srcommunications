/*Common class for data transfer*/
import {Row} from '../datatable/row.dto';
export const HTTP_METHODS={
    POST:'post',
    GET:'get',
    OPTIONS:'options'
}
export const NOTICES={
    SUCCESS:'success',
    ERROR:'error',
    WARN:'warn',
    INFO:'info'
}
export const API_URLS={
    CATEGORY_URL:"rest/createCategory"
}
export class RequestDto{
    public serviceUrl:string;
    public method:string;
    public model:any;
    public paramType:string;
    public paramValue:string;
}
export class ObjectMapper{
    static converObjToClassObj(source:Row,target:any):any{
       source.Columns.forEach(ele=>{
           if(target instanceof Category){
               if("cate_id"===ele.name){
                    target.cate_id=ele.cell.value;
               }else if("cate_name"===ele.name){
                   target.cate_name=ele.cell.value;
               }
           }
       })
    }
}
export class Category{
   cate_id:number;
   cate_name:string;
}
