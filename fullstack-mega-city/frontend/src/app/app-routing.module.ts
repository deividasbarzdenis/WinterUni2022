import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {BuildingFormComponent} from './lore/containers/building-form/building-form.component';
import {BuildingListComponent} from './lore/containers/building-list/building-list.component';
import {BuildingDetailsComponent} from "./lore/containers/building-details/building-details.component";
import {AddBuildingComponent} from "./lore/containers/building-add/add-building.component";

const routes: Routes = [
    {
        path: 'buildings',
        component: BuildingListComponent,
        data: {title: 'Building list',}
    },
    {
        path: 'buildings/new',
        component: AddBuildingComponent,
        data: {title: 'Add new building'}
    },
    {
        path: 'buildings/:id',
        component: BuildingDetailsComponent,
        data: {title: 'Building details'}
    },
    {
        path: 'buildings/update/:id',
        component: BuildingFormComponent,
        data: {title: 'Update building information'}
    },
    {
        path: '**',
        redirectTo: '/buildings',
        pathMatch: 'full'
    },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
})
export class AppRoutingModule {
}
