import {Component,Input,Output,EventEmitter} from '@angular/core';
import {Row} from './row.dto';
@Component({
    selector:'ngCell',
    templateUrl:'./ngCell.component.html'
   
})

export class NgCell{
    @Input() rows:Array<Row>=[];
    @Output() updateFn:EventEmitter<any>=new EventEmitter<any>();
    constructor(){

    }
    public deleteRow(index:number):void{
        this.rows.splice(index,1);
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
