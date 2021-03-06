import {AddBuildingComponent} from "./add-building.component";
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

describe('AddBuildingComponent', () => {
    let component: AddBuildingComponent;
    let fixture: ComponentFixture<AddBuildingComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ AddBuildingComponent ]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(AddBuildingComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
