import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import { Building } from '../model/building';
import { Observable, of, throwError as observableThrowError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import {environment} from "../../../environments/environment";

const httpOptions = {
	headers: new HttpHeaders({'Content-Type': 'application/json'})
};

const apiUrl = environment.apiBaseUrl + '/api/v1/mega/buildings';

@Injectable({
	providedIn: 'root',
})
export class BuildingService {

	constructor(private http: HttpClient) {}

	getAll(): Observable<Building[]> {
		return this.http
		  .get<Building[]>(`${apiUrl}`)
		  .pipe(map(data => data), catchError(this.handleError))
	}

	getBuildingById(id: string): Observable<Building> {
		return this.http
		  .get<Building>(`${apiUrl}/${id}`)
		  .pipe(map(data => data), catchError(this.handleError))
	}

	postBuilding(building: Building):Observable<Building>  {
		return this.http.post<Building>(`${apiUrl}/new`, building, httpOptions)
			.pipe(catchError(this.handleError));
	}

	putBuilding(building: Building) {
		return this.http.put<Building>(`${apiUrl}/update`, building, httpOptions)
			.pipe(catchError(this.handleError));
	}

	patchBuilding(building: Building) {
		return this.http.put<Building>(`${apiUrl}/patch`, building, httpOptions)
			.pipe(catchError(this.handleError));
	}
	deleteBuilding(id: string): Observable<Building>{
		return this.http.delete<Building>(`${apiUrl}/delete/${id}`, httpOptions)
			.pipe(catchError(this.handleError));
	}

	private handleError(res: HttpErrorResponse | any) {
		console.error(res.error || res.body.error);
		return observableThrowError(res.error || 'Server error');
	}
}
