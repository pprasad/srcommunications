import {Component,Input,Output,EventEmitter} from '@angular/core';
import {Row} from './row.dto';
@Component({
    selector:'ngCell',
    templateUrl:'./ngCell.component.html'
   
})

export class NgCell{
    @Input() rows:Array<Row>=[];
    @Output() updateFn:EventEmitter<any>=new EventEmitter<any>();
    @Output() deleteFn:EventEmitter<any>=new EventEmitter<any>();
    constructor(){

    }
    public deleteRow(row):void{
       let index=this.rows.indexOf(row); 
       console.info(index);
       this.rows.splice(index,1);
       this.deleteFn.emit(row);
    }
    public editRow(row:Row):void{
       row.Columns.forEach(ele=> {
           if(ele.type==='Text'){
               ele.isEditable=true;
           }else if(ele.type==='actions'){
               ele.isEditable=true;
           }
       });

    }
    public updateRow(row:Row):void{
        this.updateFn.emit(row);
        row.Columns.forEach(ele=> {
           if(ele.type==='Text'){
               ele.isEditable=false;
           }else if(ele.type==='actions'){
               ele.isEditable=false;
           }
       });
    }
   
}
