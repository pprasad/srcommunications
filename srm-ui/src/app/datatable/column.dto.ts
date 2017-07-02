import {Cell} from './cell.dto';
export class Column{
    type:string;
    name:string;
    title:string;
    cell:Cell;
    width:string="20em";
    isEditable:boolean=false;
}