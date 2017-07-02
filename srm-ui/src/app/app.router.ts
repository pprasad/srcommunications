import {Component} from '@angular/core';
import {Routes} from '@angular/router';
/*custom components*/
import {AppLayout} from './layout/layout.component';
import {AppHeader} from './common/header/header.component';
import {SidebarComponent} from './common/slider/sider.component';
import {StockEntryComponent} from './stock/stockentry.component';
import {ItemMasterComponent} from './master/itemmaster.component';
/*Tabs*/
import {TabComponent} from './tab/tab.component';
import {TabsComponent} from './tab/tabs.component';
/*ItemMaster*/
import {CategoryComponent} from './master/category/category.component';
import {NotificationComponent} from './notification/notification.component';
import {NotificationService} from './common/notification/notification.services';
import {ApiServices} from './common/services/api.services';
/*DataTable*/
import {DataTable} from './datatable/datatable.component';
import {NgCell} from './datatable/datatablecell.component';
export const routers:Routes=[
   { path: '', redirectTo: 'home', pathMatch: 'full'},
   {
       path:'home',component:AppLayout,
       children:[
         {
              path:'stockentry',component:StockEntryComponent
          },{
              path:'itementry',component:ItemMasterComponent
          }
       ]
   }
]
export const appModules={
     providers:[NotificationService,ApiServices],
     component:[AppLayout,AppHeader,SidebarComponent,StockEntryComponent,ItemMasterComponent
     ,TabsComponent,TabComponent,CategoryComponent,NotificationComponent,DataTable,NgCell],
     entryComponents:[]
}