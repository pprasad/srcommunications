import { Component, OnInit,ElementRef,Renderer} from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import {TranslateModule, TranslateLoader, TranslateStaticLoader,TranslateService} from "ng2-translate";
import {AppGlobalEvent} from '../../app.globalEvent';
@Component({
    selector:'app-header',
    templateUrl:'./header.component.html',
    styleUrls:['./header.component.scss']
})
export class AppHeader implements OnInit{
    isActive:boolean=false;
    constructor(private translate: TranslateService, public router: Router,
    private event:AppGlobalEvent) {
        
    }
    ngOnInit() {}
    public toggleSidebar():void{
         this.isActive=this.isActive?false:true;
         this.event.setMenu(this.isActive);
    }
    onLoggedout() {
        localStorage.removeItem('isLoggedin');
    }
    changeLang(language: string) {
        this.translate.use(language);
    }
}