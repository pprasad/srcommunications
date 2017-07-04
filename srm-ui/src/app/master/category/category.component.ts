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
              name:"cateId",
              title:"Category Id",
              type:"label"  
            },{
              name:"cateName",
              title:"Category Name",
              type:"Text"  
            },{
               name:null,
               title:"Action",
               type:"actions"
            }
        ];
        this.gridOptions.data=[];
        this.getDetails();
    }
    public getDetails(){
        this.request=new RequestDto();
        this.request.serviceUrl=API_URLS.CATEGORY_GET;
        this.request.method=HTTP_METHODS.GET;
        this.apiServices.execute(this.request).subscribe(res=>{ 
            this.gridOptions.data=res.response;
        });
        
    }
    public save(obj){
        console.info("Save.....");
        let cate=new Category();
        ObjectMapper.converObjToClassObj(obj,cate);
        this.request=new RequestDto();
        this.request.serviceUrl=API_URLS.CATEGORY_URL;
        this.request.method=HTTP_METHODS.POST;
        this.request.model=JSON.stringify(cate);
        this.apiServices.execute(this.request).subscribe(res=>{ 
            this.notes=new Notification(NOTICES.SUCCESS,res.result);
            this.apiServices.pushNotification(this.notes);
        });
    }
    public delete(obj){
        console.info("******delete category********");
        let cate=new Category();
        ObjectMapper.converObjToClassObj(obj,cate);
        this.request=new RequestDto();
        this.request.serviceUrl=API_URLS.CATEGORY_DELETE;
        this.request.method=HTTP_METHODS.DELETE;
        if(cate.cateId!=null){
            this.request.paramValue=cate.cateId;
            this.apiServices.execute(this.request).subscribe(res=>{ 
                this.notes=new Notification(NOTICES.SUCCESS,res.result);
                this.apiServices.pushNotification(this.notes);
            });
        }
    }
}