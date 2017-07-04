/*Common class for data transfer*/
import {Row} from '../datatable/row.dto';
export const HTTP_METHODS={
    POST:'post',
    GET:'get',
    OPTIONS:'options',
    DELETE:'delete'
}
export const NOTICES={
    SUCCESS:'success',
    ERROR:'error',
    WARN:'warn',
    INFO:'info'
}
export const API_URLS={
    CATEGORY_URL:"/saveCategory",
    CATEGORY_GET:'/getCategorys',
    CATEGORY_DELETE:'/deleteCategorys/'
}
export class RequestDto{
    public serviceUrl:string;
    public method:string;
    public model:any;
    public paramType:string;
    public paramValue:any;
}
export class Category{
   cateId:number;
   cateName:string;
   shopId:number;
}
export class ObjectMapper{
    static converObjToClassObj(source:Row,target:any):any{
       source.Columns.forEach(ele=>{
           if(target instanceof Category){
               if("cateId"===ele.name && !ele.isNew){
                    target.cateId=ele.cell.value;
               }else if("cateName"===ele.name){
                    target.cateName=ele.cell.value;
               }
           }
       })
    }
}

