import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { HelperRegex } from './helper-regex';
import { TableData } from "ngx-mat-multi-sort";
import { Location } from "@angular/common";
export class HelperParam {
    arrPageSize: number[] = [5, 10, 20, 100];
    location: Location;
    arrProperties: string[] = [];
    arrSortDirs: string[] = ["asc", "desc"];

    // Check Param Query Valid In URL After Call API
    public checkParamPage(paramInURL: string, arrPropertySort: string[]): boolean {
        paramInURL = paramInURL.substring(paramInURL.indexOf("?") + 1);
        const arrParam: string[] = paramInURL.split("&");
        this.arrProperties = arrPropertySort;
        return this.checkValidParamQuery(arrParam);
    }

    // Check Size And Type File Of Image
    public checkImage(ev: any): boolean {
        if (ev.target.files && ev.target.files[0]) {
            if (ev.target.files[0].type === 'image/jpeg' ||
                ev.target.files[0].type === 'image/png' ||
                ev.target.files[0].type === 'image/jpg') {
                if (ev.target.files[0].size < 2000000) {
                    return true;
                }
                return false;

            }
        }
        return false;
    }
    // Check Id Is Valid
    public checkIdIsValid(id: string) {
        return HelperRegex.REG_NUMBER.test(id) || id.trim() === "new";
    }
    // Check Duplicate Of Param Sort In URL 
    private checkSortDuplicate(params: string[]): boolean {
        const findDuplicates = arr => arr.filter((item, index) => arr.indexOf(item) !== index)
        params = params.filter(elm => elm.includes("sort"));
        params.forEach((element, index) => {
            params[index] = element.substring(element.indexOf("=") + 1, element.indexOf(","));
        });
        return [...new Set(findDuplicates(params))].length === 0;
    }

    // Check Param In URL Valid
    private checkValidParamQuery(params: string[]): boolean {
        if (!this.checkSortDuplicate(params)) {
            return false;
        };
        for (let i: number = 0; i < params.length; i++) {
            if (this.checkIncludesValid(params[i])) {
                if (!this.checkValueOfParam(params[i])) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return this.checkDuplicateParam(params);
    }

    // Check Param In URL Includes Property Of Table
    private checkIncludesValid(param: string): boolean {
        return param.includes("page=") || param.includes("pageSize=") || param.includes("search=") || param.includes("sort=");
    }

    // Check Value Of Param In URL Valid
    private checkValueOfParam(param: string): boolean {
        const paramName: string = param.substring(0, param.indexOf("="));
        const valueParam: string = param.substring(param.indexOf("=") + 1);
        switch (paramName) {
            case "page":
                return HelperRegex.REG_NUMBER.test(valueParam) && Number.parseInt(valueParam, 10) > 0;
            case "pageSize":
                return HelperRegex.REG_NUMBER.test(valueParam) && this.arrPageSize.includes(Number.parseInt(valueParam, 10));
            case "search":
                return HelperRegex.REG_SEARCH.test(valueParam.trim());
            case "sort":
                try {
                    const sortName: string = valueParam.substring(0, valueParam.indexOf(","));
                    const sortDir: string = valueParam.substring(valueParam.indexOf(",") + 1);
                    return this.arrProperties.includes(sortName) && this.arrSortDirs.includes(sortDir);
                } catch (err) {
                    return false;
                }
            default:
                return false;
        }
    }

    // Check Param Query In URL Duplicate
    private checkDuplicateParam(params: string[]): boolean {
        let countParamPage: number = 0;
        let countParamPageSize: number = 0;
        let countParamSearch: number = 0;
        for (let i: number = 0; i < params.length; i++) {
            const p = params[i].substring(0, params[i].indexOf("="));
            switch (p) {
                case "page":
                    countParamPage++;
                    break;
                case "pageSize":
                    countParamPageSize++;
                    break;
                case "search":
                    countParamSearch++;
                    break;
                case "sort":
                    break;
                default:
                    return false;
            }
        }
        return countParamSearch < 2 && countParamPage < 2 && countParamPageSize < 2;
    }

    // Get Params Sort To Array By Param Sort In URL
    public getSortParams(sort: string[]): string[] {
        const array: string[] = sort.toString().split(",");
        const arr: string[] = [];
        for (let i: number = 0; i < array.length; i += 2) {
            arr.push(array[i]);
        }
        return arr;
    }

    // Get Dirs Sort To Array By Param Dirs In URL
    public getSortDirs(sort: string[]): string[] {
        const array: string[] = sort.toString().split(",");
        const arr: string[] = [];
        for (let i: number = 0; i < array.length; i += 2) {
            arr.push(array[i + 1]);
        }
        return arr;
    }

    // Reload Page When Same URL
    public settingReloadSameURL(router: Router): void {
        router.routeReuseStrategy.shouldReuseRoute = () => false;
        router.events.subscribe((event) => {
            if (event instanceof NavigationEnd) {
                // Trick the Router into believing it's last link wasn't previously loaded
                router.navigated = false;
            }
        });
    }

    // Update Param In URL Not Reload Page
    public updateQueryParamInURL(location: Location, route: ActivatedRoute, router: Router, sort: string[], search: string, table: TableData<any>): void {
        console.log(table.pageIndex);
        let url: string = "";
        if (sort.length > 0) {
            url = router
                .createUrlTree([], {
                    relativeTo: route,
                    queryParams: {
                        page: table.pageIndex + 1,
                        pageSize: table.pageSize,
                        search: search,
                        sort: sort,
                    },
                })
                .toString();
        } else {
            url = router
                .createUrlTree([], {
                    relativeTo: route,
                    queryParams: { page: table.pageIndex + 1, pageSize: table.pageSize, search: search },
                })
                .toString();
        }
        location.go(url);
    }
}
