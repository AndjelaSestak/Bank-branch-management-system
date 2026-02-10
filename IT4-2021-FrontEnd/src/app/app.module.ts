import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatExpansionModule} from '@angular/material/expansion';
import { BankaComponent } from './components/main/banka/banka.component';
import { FilijalaComponent } from './components/main/filijala/filijala.component';
import { UslugaComponent } from './components/main/usluga/usluga.component';
import { KorisnikUslugeComponent } from './components/main/korisnik-usluge/korisnik-usluge.component';
import { AboutComponent } from './components/utility/about/about.component';
import { AuthorComponent } from './components/utility/author/author.component';
import { HomeComponent } from './components/utility/home/home.component';
import { HttpClientModule } from '@angular/common/http';

import{MatTableModule} from '@angular/material/table';
import {MatToolbarModule} from '@angular/material/toolbar';
import { BankaDialogComponent } from './components/dialogs/banka-dialog/banka-dialog.component';
import{MatSnackBarModule} from '@angular/material/snack-bar';
import {MatDialogModule} from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule} from '@angular/material/input';
import {FormsModule} from '@angular/forms';

import { MatDatepickerModule } from '@angular/material/datepicker';
import{MatNativeDateModule} from '@angular/material/core';
import {MatSelectModule} from '@angular/material/select';
import{MatCheckboxModule} from '@angular/material/checkbox';
import { FilijalaDialogComponent } from './components/dialogs/filijala-dialog/filijala-dialog.component';
import { KorisnikUslugeDialogComponent } from './components/dialogs/korisnik-usluge-dialog/korisnik-usluge-dialog.component';
import { UslugaDialogComponent } from './components/dialogs/usluga-dialog/usluga-dialog.component';

import { MatSortModule} from '@angular/material/sort';
import{MatPaginatorModule} from '@angular/material/paginator'

@NgModule({
  declarations: [
    AppComponent,
    BankaComponent,
    FilijalaComponent,
    UslugaComponent,
    KorisnikUslugeComponent,
    AboutComponent,
    AuthorComponent,
    HomeComponent,
    BankaDialogComponent,
    FilijalaDialogComponent,
    KorisnikUslugeDialogComponent,
    UslugaDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatIconModule,
    MatSidenavModule,
    MatListModule,
    MatGridListModule,
    MatExpansionModule,
    HttpClientModule,
    MatTableModule,
    MatToolbarModule,
    MatSnackBarModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSelectModule,
    MatCheckboxModule,
    MatSortModule,
    MatPaginatorModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
