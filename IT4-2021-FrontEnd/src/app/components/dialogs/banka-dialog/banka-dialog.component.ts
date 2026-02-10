import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Banka } from 'src/app/models/banka';
import { BankaService } from 'src/app/services/banka.service';

@Component({
  selector: 'app-banka-dialog',
  templateUrl: './banka-dialog.component.html',
  styleUrls: ['./banka-dialog.component.css']
})
export class BankaDialogComponent {

  flag!:number;

  constructor(
    public snackBar:MatSnackBar,
    public dialogRef: MatDialogRef<Banka>,
    @Inject (MAT_DIALOG_DATA) public data:Banka,
    public service: BankaService
  ){}

  public add(){
    this.service.addBanka(this.data).subscribe(
      (data)=> {
        this.snackBar.open(`Uspesno dodata banka sa nazivom: ${data.naziv}`,`U redu`,{duration:2500});
      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open(`Neuspesno dodavanje`, `Zatvori`, {duration:1500});
    }
  }
  public update(){

    this.service.updateBanka(this.data).subscribe(
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

    this.service.deleteBanka(this.data.id).subscribe(
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
