import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {HeaderComponent} from './component/header.component';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {FooterComponent} from './component/footer.component';
import {AppRoutingModule} from '../app-routing.module';
import {HttpClientModule} from '@angular/common/http';
import {HttpService} from '../services/http.service';


@NgModule({
    imports: [
        CommonModule,
        ReactiveFormsModule,
        FormsModule,
        AppRoutingModule,
        HttpClientModule
    ],
    declarations: [
        HeaderComponent,
        FooterComponent,
    ],
    providers: [
        HttpService
    ],
    exports: [
        HeaderComponent,
        FooterComponent,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CoreModule {
}
