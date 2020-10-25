import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {CoreModule} from './core/core.module';
import {HomeComponent} from './component/home.component';
import {FormsModule} from '@angular/forms';
import {ShareComponent} from './component/share.component';
import {HomeService} from './service/home.service';
import {SanitizeHtmlPipe} from './pipe/html-safe.pipe';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ShareComponent,
    SanitizeHtmlPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    CoreModule
  ],
  providers: [
    HomeService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
