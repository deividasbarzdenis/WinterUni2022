import {Component, OnInit} from '@angular/core';
import {Building} from "../../model/building";
import {ActivatedRoute, Router} from "@angular/router";
import {BuildingService} from '../../services/building.service';

@Component({
    selector: 'app-building-details',
    templateUrl: './building-details.component.html',
    styleUrls: ['./building-details.component.scss']
})

export class BuildingDetailsComponent implements OnInit {
    building: Building = {id: '', name: '', address: '', index: '', sectorCode: '', energyUnits: 0, energyUnitMax: 0};
    isLoadingResults = true;

    constructor(private route: ActivatedRoute, private api: BuildingService, private router: Router) {
    }

    ngOnInit(): void {
        this.getBuildingDetails(this.route.snapshot.params.id);
    }

    getBuildingDetails(id: string) {
        this.api.getBuildingById(id).subscribe((data: any) => {
        this.building = data;
        console.log(this.building);
        this.isLoadingResults = false;
        })
    }

    deleteBuilding(id: any) {
        this.isLoadingResults = true;
        this.api.deleteBuilding(id)
            .subscribe(res => {
                    this.isLoadingResults = false;
                    this.router.navigate(['/buildings']).then();
                }, (err) => {
                    console.log(err);
                    this.isLoadingResults = false;
                }
            );
    }

    setColor(status: string) {
        let color: string;
        if (status === 'Positive') {
            color = 'orange-color';
        } else if (status === 'Recovered') {
            color = 'green-color';
        } else {
            color = 'red-color';
        }
        return color;
    }
}
