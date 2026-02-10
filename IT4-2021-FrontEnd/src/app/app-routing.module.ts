import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BankaComponent } from './components/main/banka/banka.component';
import { FilijalaComponent } from './components/main/filijala/filijala.component';
import { KorisnikUslugeComponent } from './components/main/korisnik-usluge/korisnik-usluge.component';
import { UslugaComponent } from './components/main/usluga/usluga.component';
import { AboutComponent } from './components/utility/about/about.component';
import { AuthorComponent } from './components/utility/author/author.component';
import { HomeComponent } from './components/utility/home/home.component';

const routes: Routes = [
  {path:'banka',component:BankaComponent},
  {path:'filijala',component:FilijalaComponent},
  {path:'usluga',component:UslugaComponent},
  {path:'korisnik-usluge',component:KorisnikUslugeComponent},
  {path:'home',component:HomeComponent},
  {path:'about',component:AboutComponent},
  {path:'author',component:AuthorComponent},
  {path:'',component:HomeComponent,pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }