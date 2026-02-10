import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { USLUGA_URL, USLUGE_BY_FILIJALA_URL } from '../constants';
import { Usluga } from '../models/usluga';

@Injectable({
  providedIn: 'root'
})
export class UslugaService {

  constructor(private httpClient:HttpClient) { }

  public getAllUslugas():Observable<any>{
    return this.httpClient.get(`${USLUGA_URL}`);
  }

  public getUslugeByFilijala(filijalaId:number):Observable<any>{
      return this.httpClient.get(`${USLUGE_BY_FILIJALA_URL}/${filijalaId}`)
  }

  public addUsluga(usluga:Usluga):Observable<any>{
    return this.httpClient.post(`${USLUGA_URL}`,usluga);
  }

  public updateUsluga(usluga:Usluga):Observable<any>{
    return this.httpClient.put(`${USLUGA_URL}/id/${usluga.id}`,usluga);
  }

  public deleteUsluga(uslugaId:number):Observable<any>{
    return this.httpClient.delete(`${USLUGA_URL}/id/${uslugaId}`, {responseType:"text"});
  }
}
