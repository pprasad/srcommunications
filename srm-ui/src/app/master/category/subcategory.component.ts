import {Component,OnInit} from '@angular/core';
import {FormBuilder,FormGroup,Validators} from '@angular/forms';
@Component({
    selector:'sub-category',
    templateUrl:'subcategory.component.html'
})
export class SubCategoryComponent implements OnInit{
    subcateForm:FormGroup;
    constructor(private fb:FormBuilder){}
    ngOnInit(){
        this.subcateForm=this.fb.group({
            productCode:['',Validators.required],
            productName:['',Validators.required],
            productType:['',Validators.required]
        })
    }
}