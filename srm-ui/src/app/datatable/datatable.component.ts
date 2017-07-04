import {Component,Input,OnInit,AfterViewChecked,ViewEncapsulation,Output,EventEmitter,
    DoCheck} from '@angular/core';
import {Cell} from './cell.dto';
import {Column} from './column.dto';
import {Row} from './row.dto';
@Component({
    selector:'ngGrid',
    templateUrl:'./datatable.component.html',
    styleUrls:['./datatable.component.scss'],
    encapsulation:ViewEncapsulation.None
})
export class DataTable implements OnInit,DoCheck{
    @Input("gridOptions") gridOptions:any;
    @Output() updateFn:EventEmitter<any>=new EventEmitter<any>();
    @Output() deleteFn:EventEmitter<any>=new EventEmitter<any>();
    rows:Row[]=[];
    column:Column[];
    isLoad:boolean=true;
    constructor(){}
    ngOnInit(){
        if(this.gridOptions.columnDefs!=undefined){
            this.column=Object.assign(new Array<Column>(),this.gridOptions.columnDefs);
        }
    }
    ngDoCheck(){
        if(this.gridOptions.columnDefs!=undefined){
           if(this.gridOptions.data.length!=0 && this.isLoad){
               this.isLoad=false;
               this.gridOptions.data.forEach(row=>{
                 let row_index=new Row();
                 this.rows.push(row_index);
                 this.column.forEach(col=>{
                      let cell=new Cell();
                      let column=new Column();
                      column=col;
                      cell.value=row[col.name];
                      column.cell=cell;
                      row_index.addColumns(column);
                });
            });
           }
        }
    }
    public addRow():void{
        let row=new Row();
        let index=this.rows.length>0?this.rows[this.rows.length-1].Columns[0].cell.value:0;
        this.column.forEach(col=>{
            let cell=new Cell();
            if(col.type=='label'){
              cell.value=index+1;
            }else{
               col.isEditable=true;
            }
            col.isNew=true;
            col.cell=cell;
            row.addColumns(col);
        });
        this.rows.push(row);
   }
   public update(obj):void{
        this.updateFn.emit(obj);
   }
   public delete(obj):void{
       this.deleteFn.emit(obj);
   }
}