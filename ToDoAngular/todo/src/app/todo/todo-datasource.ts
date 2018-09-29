import { DataSource } from "@angular/cdk/table";
import { Observable } from "rxjs";
import { Task } from "../model/Task";

export class ToDoDatasource extends DataSource<Task>{
 
    constructor(private _list$: Observable<Task[]>) {
        super();
    }

    connect(): Observable<Task[]> {
        return this._list$;
    }

    disconnect() {
    }   
}