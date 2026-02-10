import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { KorisnikUsluge } from '../models/korisnik-usluge';
import { KORISNIK_URL } from '../constants';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class KorisnikUslugeService {

  constructor(private httpClient:HttpClient) { }

  public getAllKorisniks():Observable<any>{
    return this.httpClient.get(`${KORISNIK_URL}`);
  }

  public addKorisnik(korisnik:KorisnikUsluge):Observable<any>{
    return this.httpClient.post(`${KORISNIK_URL}`,korisnik);
  }

  public updateKorisnik(korisnik:KorisnikUsluge):Observable<any>{
    return this.httpClient.put(`${KORISNIK_URL}/id/${korisnik.id}`,korisnik);
  }

  public deleteKorisnik(korisnikId:number):Observable<any>{
    return this.httpClient.delete(`${KORISNIK_URL}/id/${korisnikId}`, {responseType:"text"});
  }
}
