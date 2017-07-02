import {Injectable} from "@angular/core";
import {Subject} from 'rxjs/Subject';

@Injectable()
export class AppGlobalEvent{
    private sliderMenu=new Subject<boolean>();
    public sliderObser=this.sliderMenu.asObservable();
    public setMenu(value:boolean){
        this.sliderMenu.next(value);
    }
}