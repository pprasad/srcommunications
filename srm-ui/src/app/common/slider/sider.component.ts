import { Component} from '@angular/core';
import {AppGlobalEvent} from '../../app.globalEvent';
@Component({
    selector: 'app-sidebar',
    templateUrl: './sidebar.component.html',
    styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent {
    isActive = false;
    showMenu = '';
    menuWidth:number=50;
    iconShow:boolean=true;
    constructor(private event:AppGlobalEvent){
        this.event.sliderObser.subscribe(val=>{
           this.toggleClass(val)
        })
    }
    toggleClass(val){
       this.iconShow=!val; 
       this.menuWidth=val?this.menuWidth+185:this.menuWidth-185;
    }
    eventCalled() {
        this.isActive = !this.isActive;
    }
    addExpandClass(element: any) {
        if (element === this.showMenu) {
            this.showMenu = '0';
        } else {
            this.showMenu = element;
        }
    }
}
