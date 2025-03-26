import { Injectable } from "@angular/core";
import { Disco } from "../entidades/disco";
import { HttpClient } from "@angular/common/http";
import { map, Observable } from "rxjs";

@Injectable({
    providedIn : "root"
})
export class ServicioDiscos {

    public constructor(private httpClient:HttpClient){
    }

    public insertar(disco:Disco):Observable<any>{
        return this.httpClient.post("http://localhost:8080/discos", disco)
    }

    public listar():Observable<any>{
        return this.httpClient.get("http://localhost:8080/discos")
    }
    
    public buscarPorId(id:number):Observable<any>{
        return this.httpClient.get(`http://localhost:8080/discos/${id}`)
            .pipe(
                map( (d:any) => {
                    d.grupo = d.grupo.toUpperCase()
                    return d
                })
            )
    }

}

