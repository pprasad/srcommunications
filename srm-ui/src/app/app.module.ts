import 'rxjs/add/operator/map';
import { NgModule }      from '@angular/core';
import { CommonModule} from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpModule,Http} from '@angular/http';
import {LocationStrategy,HashLocationStrategy} from '@angular/common';
import {RouterModule} from '@angular/router';
import {TranslateModule, TranslateLoader, TranslateStaticLoader,TranslateService} from "ng2-translate";
/*Angular2 with bootstrap modules*/
import { ModalModule } from 'angular2-modal';
import { BootstrapModalModule} from 'angular2-modal/plugins/bootstrap';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
/*AppComponent is main entry class*/
import {AppComponent} from './app.component';
import {routers,appModules} from './app.router';
import {AppGlobalEvent} from './app.globalEvent';
export function createTranslateLoader(http: Http) {
  return new TranslateStaticLoader(http,'i18n','.json');
}
@NgModule({
   imports:[CommonModule,BrowserModule,HttpModule,FormsModule,ReactiveFormsModule,TranslateModule.forRoot(),
   ModalModule.forRoot(),BootstrapModalModule,RouterModule.forRoot(routers),NgbModule],
   exports:[CommonModule,TranslateModule],
   providers:[{provide: TranslateLoader,useFactory:(http: Http)=>new TranslateStaticLoader(http,'i18n','.json'),deps: [Http]},
   {provide:LocationStrategy,useClass:HashLocationStrategy},AppGlobalEvent,appModules.providers],
   declarations:[AppComponent,appModules.component],
   entryComponents:[],
   bootstrap:[AppComponent]
})
export class AppModule { 
   constructor(private translate:TranslateService){
        translate.addLangs(["en", "fr"]);
        translate.setDefaultLang('en');
        let browserLang: string = translate.getBrowserLang();
        translate.use(browserLang.match(/en|fr/) ? browserLang:'en');
   }
}