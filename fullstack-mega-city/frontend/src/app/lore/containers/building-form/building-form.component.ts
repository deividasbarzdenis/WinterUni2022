import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Building} from '../../model/building';
import {ActivatedRoute, Router} from '@angular/router';
import {BuildingService} from '../../services/building.service';
import {HttpErrorResponse} from "@angular/common/http";

@Component({
    selector: 'app-building-form',
    templateUrl: './building-form.component.html',
    styleUrls: ['./building-form.component.scss'],
})
export class BuildingFormComponent implements OnInit {
    building: Building = null;
    form: FormGroup = this.initForm();
    isLoadingResults = false;

    constructor(
        private router: Router,
        private route: ActivatedRoute,
        private buildingService: BuildingService,
        private formBuilder: FormBuilder
    ) {
    }

    ngOnInit() {
        let id = this.route.snapshot.paramMap.get('id');

        if (id !== null) {
            this.buildingService.getBuildingById(id).subscribe((data) => {
                this.form = this.initForm(data);
                this.building = data;
            });
        }
    }

    onFormSubmit() {
        this.isLoadingResults = true;
        this.buildingService.putBuilding({...this.form.value, id: this.building.id})
            .subscribe((res: any) => {
                    const id = res.id;
                    this.isLoadingResults = false;
                    this.router.navigate(['/buildings', id]).then();
                }, (err: any) => {
                    console.log(err);
                    this.isLoadingResults = false;
                }
            );
    }

    initForm(building?: Building) {
        return this.formBuilder.group({
            name: new FormControl(
                building?.name || '',
                [Validators.required, Validators.maxLength(50)]
            ),
            address: new FormControl(
                building?.address || '',
                [Validators.required, Validators.maxLength(50)]
            ),
            index: new FormControl(
                building?.index || '', [Validators.required, Validators.min(5),
                    Validators.maxLength(6), Validators.pattern('(^NO)([A-Z]{2}|\\S)(\\d{2,3})')]
            ),
            sectorCode: new FormControl({
                    value: building?.sectorCode || '',
                    disabled: building?.id
                }
            ),
            energyUnitMax: new FormControl(
                {
                    value: building?.energyUnitMax || '',
                    disabled: building?.id,
                }
            ),
            energyUnits: new FormControl(
                building?.energyUnits || '',
                [Validators.required, Validators.max(building?.energyUnitMax)]
            ),
        });
    }

    hasError(path: string, errorCode: string) {
        return this.form && this.form.hasError(errorCode, path);
    }

    navigateToBuildingsList() {
        this.router.navigate(['/buildings']).then();
    }

}
