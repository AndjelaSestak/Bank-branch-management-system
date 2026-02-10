import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Banka } from 'src/app/models/banka';
import { BankaService } from 'src/app/services/banka.service';
import { BankaDialogComponent } from '../../dialogs/banka-dialog/banka-dialog.component';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-banka',
  templateUrl: './banka.component.html',
  styleUrls: ['./banka.component.css']
})
export class BankaComponent implements OnInit, OnDestroy{
  
  displayedColumns = ['id','naziv','kontakt','pib','actions'];
  dataSource!: MatTableDataSource<Banka>;
  subscription!:Subscription;

  @ViewChild(MatSort,{static:false}) sort!:MatSort;
  @ViewChild(MatPaginator,{static:false}) paginator!:MatPaginator;

  constructor(private service:BankaService,
              public dialog:MatDialog
  ){}

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
  
  ngOnInit(): void {
    this.loadData();
  }

  public loadData() {
    this.subscription = this.service.getAllBankas().subscribe(
      (data) => {
        //console.log(data);
        this.dataSource = new MatTableDataSource(data);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator=this.paginator;
      } 
    ),
    (error: Error) => {
      console.log(error.name + ' ' + error.message)
    };
    
  }

  public openDialog(flag:number, id?:number, naziv?:string, pib?:number, kontakt?:string){

    const dialogRef = this.dialog.open(BankaDialogComponent, {data : {id,naziv,pib,kontakt}});
    dialogRef.componentInstance.flag = flag;
    dialogRef.afterClosed().subscribe(
      (result)=> {
        if(result == 1){
          this.loadData();
        }
      }
    )
  }

  public applyFilter(filter:any){
    filter=filter.target.value;
    filter = filter.trim();
    filter= filter.toLocaleLowerCase();
    this.dataSource.filter = filter;
  }
}
