import {Component,Input} from '@angular/core';
@Component({
    selector:'tab',
    template:`<div [hidden]="!active" class="pane">
                <ng-content></ng-content>
             </div>`,
    styles: [`.pane{
      padding: 1em;
    }`]
})
export class TabComponent{
    @Input("title") title:string;
    @Input() active:boolean=false;
    constructor(){
        
    }
}