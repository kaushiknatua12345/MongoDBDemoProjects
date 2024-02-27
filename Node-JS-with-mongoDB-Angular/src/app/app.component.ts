import { Component, ViewChild } from '@angular/core';
import { CommonService } from './service';
import { OnInit } from '@angular/core';
import {FormGroup,FormControl,Validators,FormsModule, } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Angular Crud Operation With MongoDB';
  dataSource: MatTableDataSource<User>;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  displayedColumns: string[] = ['firstName', 'lastName','email' ,'phoneNo','Edit', 'Delete'];

  id :any;
  firstName : any;
  lastName: any;
  email : any;
  phoneNo: any;
  constructor(private newService :CommonService,) {
     }
   Repdata;
   valbutton ="Save";

   ngOnInit() {
    this.newService.GetUser().subscribe((data :any) => {
       this.Repdata = data
       console.log("get data => ",data);
       this.dataSource = new MatTableDataSource(data);
       this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
      });
  }
  ngAfterViewInit() {
    // this.dataSource.paginator = this.paginator;
    // this.dataSource.sort = this.sort;
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // Datasource defaults to lowercase matches
    this.dataSource.filter = filterValue;
  }
  onSave = function(user,isValid ?: boolean) {
    user.mode= this.valbutton;
    console.log("edit => ", user);
     this.newService.saveUser(user)
     .subscribe(data =>  {  alert(data.data);

       this.ngOnInit();
     }
     , error => this.errorMessage = error )

   }
   edit = function(userInfo) {
     console.log("edit => ",userInfo);
   this.id = userInfo._id;
   this.firstName= userInfo.firstName;
   this.lastName= userInfo.lastName;
   this.email= userInfo.email;
   this.phoneNo= userInfo.phoneNo;
   this.valbutton ="Update";
   }

   delete = function(id) {
   this.newService.deleteUser(id)
   .subscribe(data =>   {
     alert(data.data) ;
     this.ngOnInit();
  }, error => this.errorMessage = error )
   }
}

export interface User {
  firstName: string;
  lastName: string;
  email: string;
  phoneNo:string;

}
