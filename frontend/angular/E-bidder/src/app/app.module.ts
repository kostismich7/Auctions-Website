import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {StoreDevtoolsModule} from '@ngrx/store-devtools';


import { AppComponent } from './app.component';
import {AppRoutingModule} from "./app-routing.module";
import {WelcomePageModule} from "./welcome-page/welcome-page.module";
import {MainPageModule} from "./main-page/main-page.module";
import {HttpClientModule} from "@angular/common/http";
import {StoreModule} from "@ngrx/store";
import {reducers} from "./store/app.reducer";
import {EffectsModule} from "@ngrx/effects";
import {AuthEffect} from "./auth/store/auth.effect";
import {AuthModule} from "./auth/auth.module";
import {MainPageEffect} from "./main-page/store/main-page.effect";


@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    WelcomePageModule,
    HttpClientModule,
    StoreModule.forRoot(reducers),
    EffectsModule.forRoot([AuthEffect]),
    StoreDevtoolsModule.instrument({
      maxAge: 20
    }),
    AuthModule,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
