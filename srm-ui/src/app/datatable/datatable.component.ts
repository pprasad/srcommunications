import {Component,Input,OnInit,AfterViewChecked,ViewEncapsulation,Output,EventEmitter} from '@angular/core';
import {Cell} from './cell.dto';
import {Column} from './column.dto';
import {Row} from './row.dto';
@Component({
    selector:'ngGrid',
    templateUrl:'./datatable.component.html',
    styleUrls:['./datatable.component.scss'],
    encapsulation:ViewEncapsulation.None
})
export class DataTable implements OnInit{
    @Input("gridOptions") gridOptions:any;
    @Output() updateFn:EventEmitter<any>=new EventEmitter<any>();
    rows:Row[]=[];
    column:Column[];
    constructor(){}
    ngOnInit(){
       console.info(this.gridOptions);
        if(this.gridOptions.columnDefs!=undefined){
            this.column=Object.assign(new Array<Column>(),this.gridOptions.columnDefs);
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
    public addRow():void{
        let row=new Row();
        let index=this.rows[this.rows.length-1].Columns[0].cell.value;
        this.column.forEach(col=>{
            let cell=new Cell();
            if(col.type=='label'){
              cell.value=index+1;
            }
            col.cell=cell;
            row.addColumns(col);
        });
        this.rows.push(row);
    }
   public update(obj):void{
        this.updateFn.emit(obj);
    }
}