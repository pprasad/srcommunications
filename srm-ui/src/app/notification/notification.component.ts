import {Component} from '@angular/core';
import {Notification} from '../common/notification/notification.model';
import {NotificationService} from '../common/notification/notification.services';

@Component({
    selector:'notification',
    templateUrl:'./notification.component.html',
    styleUrls:['./notification.component.scss']
})
export class NotificationComponent{
    private _notes: Notification[];

    constructor(private _notifications:NotificationService){
        this._notes = new Array<Notification>();
        _notifications.notesAdded.subscribe(note=>{
            this._notes.push(note);
            setTimeout(() => { this.hide.bind(this)(note) }, 3000);
        });
    }
    private hide(note) {
        let index = this._notes.indexOf(note);
        if(index >= 0) {
            this._notes.splice(index, 1);
        }
    }
}