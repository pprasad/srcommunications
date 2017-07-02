import {Column} from './column.dto';
export class Row{
    column:Array<Column>;
    constructor(){
        this.column=new Array<Column>();
    }
    public addColumns(column:Column):void{
        this.column.push(Object.assign(new Column(),column));
    }
    get Columns():Column[]{
        return this.column
    }
}