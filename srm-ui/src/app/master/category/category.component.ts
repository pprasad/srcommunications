import {Component,OnInit} from '@angular/core';
import {FormGroup,FormControl,FormBuilder} from '@angular/forms';
import {Notification} from '../../common/notification/notification.model';
import {ApiServices} from '../../common/services/api.services';
import {HTTP_METHODS,NOTICES,RequestDto,API_URLS,ObjectMapper,Category} from '../../dto/dto.component';
@Component({
    selector:'item-category',
    templateUrl:'./category.component.html'
})
export class CategoryComponent implements OnInit{
    notes:Notification;
    catergoryForm:FormGroup;
    request:RequestDto;
    gridOptions:any={};
    constructor(private fb:FormBuilder,private apiServices:ApiServices){
     
    }
    ngOnInit(){
        this.catergoryForm=this.fb.group({
              categoryname:['']
        })
        this.gridOptions.filter=true;
        this.gridOptions.columnDefs=[
            {
              name:"cate_id",
              title:"Category Id",
              type:"label"  
            },{
              name:"cate_name",
              title:"Category Name",
              type:"Text"  
            },{
               name:null,
               title:"Action",
               type:"actions"
            }
        ];
        this.gridOptions.data=[{"cate_id":1,"cate_name":"Books"},
        {"cate_id":2,"cate_name":"Pens"},{"cate_id":3,"cate_name":"Gift Artials"},
        {"cate_id":4,"cate_name":"Mobiles"},{"cate_id":5,"cate_name":"Mobilescases"}];
    }
    public save(obj){
        console.info("Save.....");
        console.info(obj);
        console.info(this.catergoryForm.value);
        let cate=new Category();
        ObjectMapper.converObjToClassObj(obj,cate);
        console.info(cate);
        this.request=new RequestDto();
        this.request.serviceUrl=API_URLS.CATEGORY_URL;
        this.request.method=HTTP_METHODS.POST;
        this.request.model=cate;
        this.apiServices.execute(this.request).subscribe(res=>{ 
            this.notes=new Notification(NOTICES.SUCCESS,"Succs");
            this.apiServices.pushNotification(this.notes);
        });
       
    }
}