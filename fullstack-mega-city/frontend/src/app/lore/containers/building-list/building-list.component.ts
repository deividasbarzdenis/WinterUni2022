import { Component, OnInit } from '@angular/core';
import { Building } from '../../model/building';
import { Router } from '@angular/router';
import { BuildingService } from '../../services/building.service';

@Component({
	selector: 'app-building-list',
	templateUrl: './building-list.component.html',
	styleUrls: ['./building-list.component.scss'],
})
export class BuildingListComponent implements OnInit {
	displayedColumns: string[] = ['id', 'name', 'address', 'sectorCode', 'actions'];
	dataSource: Building[] = [];
	isLoadingResults = true;

	constructor(private router: Router, private buildingService: BuildingService) {}

	ngOnInit(): void {
		this.buildingService.getAll()
			.subscribe(buildings => {
				this.dataSource = buildings;
				console.log(this.dataSource);
				this.isLoadingResults = false;
			}, error => {
				console.log(error);
				this.isLoadingResults = false;
		}
			);
	}

	deleteBuilding(id: any) {
		this.isLoadingResults = true;
		this.buildingService.deleteBuilding(id)
			.subscribe(res => {
					this.isLoadingResults = false;
					this.router.navigate(['/buildings']).then();
				}, (err) => {
					console.log(err);
					this.isLoadingResults = false;
				}
			);
	}
}
