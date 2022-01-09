import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {BuildingService} from "../../services/building.service";
import {Building} from "../../model/building";

@Component({
    selector: 'app-add-building',
    templateUrl: './add-building.component.html',
    styleUrls: ['./add-building.component.scss']
})
export class AddBuildingComponent implements OnInit {
    building: Building = null;
    buildingForm: FormGroup = this.initForm();
    name = '';
    address = '';
    index: '';
    sectorCode = '';
    energyUnits: '' ;
    energyUnitMax: '';
    isLoadingResults = false;


    constructor(private router: Router, private api: BuildingService, private formBuilder: FormBuilder) {
    }

    ngOnInit(): void {

    }
    initForm(){
        return  this.buildingForm = this.formBuilder.group({
            name: new FormControl(
                '',
                [
                    Validators.required,
                    Validators.maxLength(50)
                ]
            ),
            address: new FormControl(
                '',
                [
                    Validators.required,
                    Validators.maxLength(50)
                ]
            ),
            index: new FormControl(
                '',
                [
                    Validators.required,
                    Validators.pattern('(^NO)([A-Z]{2}|\\S)(\\d{2,3})'),
                    Validators.min(5),
                    Validators.maxLength(6)
                ]
            ),
            sectorCode: new FormControl(
                '',
                [
                    Validators.required
                ]
            ),
            energyUnitMax: new FormControl(
                0,
                [
                    Validators.required,
                ]
            ),
            energyUnits: new FormControl(
                 0,
                [
                    Validators.required,
                    Validators.max(5),
                ]
            ),
        });
    }

    onFormSubmit() {
        this.isLoadingResults = true;
        this.api.postBuilding({...this.buildingForm.value})
            .subscribe((res: any) => {
                const id = res.id;
                this.isLoadingResults = false;
                this.router.navigate(['/buildings', id]).then();
            }, (err: any) => {
                console.log(err);
                this.isLoadingResults = false;
            });
    }
    hasError(path: string, errorCode: string) {
        return this.buildingForm && this.buildingForm.hasError(errorCode, path);
    }
}
