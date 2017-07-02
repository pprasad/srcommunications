import {Component}  from '@angular/core';
import {AppGlobalEvent} from '../app.globalEvent';
@Component({
   selector:'app-layout',
   templateUrl:'./layout.component.html',
   styleUrls:['./layout.component.scss']
})
export class AppLayout{
    loginFlag:string;
    marginleft:number=30;
    constructor(private event:AppGlobalEvent){
        this.event.sliderObser.subscribe(val=>{
           this.toggleClass(val)
        })
    }
    // constructor(){
    //   //localStorage.setItem('isLoggedin', 'true');
    //    this.loginFlag=localStorage.getItem('isLoggedin');
    //    console.info('loginFlag{}'+this.loginFlag);
    // }
    toggleClass(val){
       this.marginleft=val?this.marginleft+185:this.marginleft-185;
    }
}