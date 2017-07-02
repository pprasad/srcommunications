import 'reflect-metadata';
import 'zone.js/dist/zone';
import 'ts-helpers';
import 'rxjs/Rx';
import 'rxjs/add/operator/map';
import { platformBrowserDynamic} from '@angular/platform-browser-dynamic';
import { enableProdMode } from '@angular/core';
import {AppModule} from "./app.module";
platformBrowserDynamic().bootstrapModule(AppModule).
then((success:any)=>console.info("Started Application..."))
.catch((err:any)=>console.error(err));