import { ApplicationConfig } from "@angular/core";
import { provideRouter } from "@angular/router";
import {
  HttpClientModule,
  HttpClient,
  provideHttpClient,
  withFetch,
} from "@angular/common/http";

import { routes } from "./app.routes";

export const appConfig: ApplicationConfig = {
  providers: [provideRouter(routes), provideHttpClient(withFetch())],
};
