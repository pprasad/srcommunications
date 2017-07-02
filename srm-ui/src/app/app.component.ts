import {Component,OnInit,AfterViewInit,Input,ElementRef,Renderer,Attribute,ViewContainerRef} from "@angular/core";
import {Routes,RouterModule,Router} from "@angular/router"
import {TranslateService} from 'ng2-translate';
import {ApiServices} from './common/services/api.services';
@Component({
    selector:"app-container",
    templateUrl:'./app.component.html'
})
export class AppComponent{
   constructor(renderer: Renderer,private api:ApiServices){
       let rootEle=renderer.selectRootElement('app-container');
       let envType=rootEle.getAttribute('envType');
       ApiServices.ENV_TYPE=envType;
    }
 }
