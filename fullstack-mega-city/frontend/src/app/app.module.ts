import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {BuildingFormComponent} from './lore/containers/building-form/building-form.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {TranslateLoader, TranslateModule} from '@ngx-translate/core';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {ReactiveFormsModule, FormsModule} from '@angular/forms';
import {MatDividerModule} from '@angular/material/divider';
import {MatButtonModule} from '@angular/material/button';
import {BuildingListComponent} from './lore/containers/building-list/building-list.component';
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {MatSortModule} from "@angular/material/sort";
import {MatIconModule} from "@angular/material/icon";
import {MatCardModule} from "@angular/material/card";
import {MatSelectModule} from '@angular/material/select';
import {MatButtonToggleModule} from "@angular/material/button-toggle";
import {MatSlideToggleModule} from "@angular/material/slide-toggle";
import {MatSliderModule} from "@angular/material/slider";
import {BuildingDetailsComponent} from "./lore/containers/building-details/building-details.component";
import {AddBuildingComponent} from "./lore/containers/building-add/add-building.component";

@NgModule({
    declarations: [
        AppComponent,
        AddBuildingComponent,
        BuildingFormComponent,
        BuildingListComponent,
        BuildingDetailsComponent,],
    imports: [
        BrowserModule,
        HttpClientModule,
        TranslateModule.forRoot({
            loader: {
                provide: TranslateLoader,
                useFactory: HttpLoaderFactory,
                deps: [HttpClient],
            },
        }),
        AppRoutingModule,
        BrowserAnimationsModule,
        MatToolbarModule,
        TranslateModule,
        MatFormFieldModule,
        MatInputModule,
        MatPaginatorModule,
        MatProgressSpinnerModule,
        MatSortModule,
        MatIconModule,
        FormsModule,
        ReactiveFormsModule,
        MatDividerModule,
        MatButtonModule,
        MatTableModule,
        MatCardModule,
        MatSliderModule,
        MatSlideToggleModule,
        MatButtonToggleModule,
        MatSelectModule,
    ],
    providers: [],
    bootstrap: [AppComponent],
})
export class AppModule {
}

export function HttpLoaderFactory(http: HttpClient): TranslateHttpLoader {
    return new TranslateHttpLoader(http);
}
