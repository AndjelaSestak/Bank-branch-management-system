import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { KorisnikUsluge } from 'src/app/models/korisnik-usluge';
import { KorisnikUslugeService } from 'src/app/services/korisnik-usluge.service';

@Component({
  selector: 'app-korisnik-usluge-dialog',
  templateUrl: './korisnik-usluge-dialog.component.html',
  styleUrls: ['./korisnik-usluge-dialog.component.css']
})
export class KorisnikUslugeDialogComponent {

  flag!:number;

  constructor(
    public snackBar:MatSnackBar,
    public dialogRef: MatDialogRef<KorisnikUsluge>,
    @Inject (MAT_DIALOG_DATA) public data:KorisnikUsluge,
    public service: KorisnikUslugeService
  ){}
  
  public add(){
    this.service.addKorisnik(this.data).subscribe(
      (data)=> {
        this.snackBar.open(`Uspesno dodat korisnik sa nazivom: ${data.ime}`,`U redu`,{duration:2500});
      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open(`Neuspesno dodavanje`, `Zatvori`, {duration:1500});
    }
  }
  public update(){

    this.service.updateKorisnik(this.data).subscribe(
      (data)=> {
        this.snackBar.open(`Uspesno azurirana banka sa nazivom: ${data.naziv}`,`U redu`,{duration:2500});
      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open(`Neuspesno azuriranje`, `Zatvori`, {duration:1500});
    }
  }
  public delete(){

    this.service.deleteKorisnik(this.data.id).subscribe(
      (data)=> {
        this.snackBar.open(`${data}`,`U redu`,{duration:2500});
      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open(`Neuspesno brisanje`, `Zatvori`, {duration:1500});
    }
  }

  public cancel(){
    this.dialogRef.close();
    this.snackBar.open(`Odustali ste od izmena`,`Zatvori`,{duration:1500});
  }
}
