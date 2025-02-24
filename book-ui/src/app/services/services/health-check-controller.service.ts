/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { ping } from '../fn/health-check-controller/ping';
import { Ping$Params } from '../fn/health-check-controller/ping';

@Injectable({ providedIn: 'root' })
export class HealthCheckControllerService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `ping()` */
  static readonly PingPath = '/health/ping';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `ping()` instead.
   *
   * This method doesn't expect any request body.
   */
  ping$Response(params?: Ping$Params, context?: HttpContext): Observable<StrictHttpResponse<string>> {
    return ping(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `ping$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  ping(params?: Ping$Params, context?: HttpContext): Observable<string> {
    return this.ping$Response(params, context).pipe(
      map((r: StrictHttpResponse<string>): string => r.body)
    );
  }

}
