import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BANKA_URL } from '../constants';
import { Banka } from '../models/banka';

@Injectable({
  providedIn: 'root'
})
export class BankaService {

  constructor(private httpClient:HttpClient) { }

  public getAllBankas():Observable<any>{
    return this.httpClient.get(`${BANKA_URL}`);
  }

  public addBanka(banka:Banka):Observable<any>{
    return this.httpClient.post(`${BANKA_URL}`,banka);
  }

  public updateBanka(banka:Banka):Observable<any>{
    return this.httpClient.put(`${BANKA_URL}/id/${banka.id}`,banka);
  }

  public deleteBanka(bankaId:number):Observable<any>{
    return this.httpClient.delete(`${BANKA_URL}/id/${bankaId}`, {responseType:"text"});
  }


}
