import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UploadComponent } from './components/upload/upload.component';
import { ViewImageComponent } from './components/view-image/view-image.component';
import { CitiesComponent } from './components/cities/cities.component';
import { GalleryComponent } from './components/gallery/gallery.component';

const routes: Routes = [
  {path: "", component: UploadComponent},
  {path: "image/:postId", component: ViewImageComponent},
  {path: "cities", component: CitiesComponent},
  {path: "gallery", component: GalleryComponent},
  {path: "**", redirectTo: "/", pathMatch: "full"}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
