import { AngularFireStorage } from '@angular/fire/storage';
import { formatDate } from '@angular/common';
import { ErrorValidateServer } from '../models/ErrorValidateServer';
import { ErrorSqlServer } from '../models/ErrorSqlServer';

export class Helper {
    storage: AngularFireStorage;
    public deleteImageFireBase(storage: AngularFireStorage, url: string): Promise<any> {
        return storage.storage.refFromURL(url).delete();
    }

    public getDateTime(): string {
        return formatDate(new Date(), 'dd/MM/YYYY', 'en');
    }


    public getUsername(firstName: string, midName: string, lastName: string): string {
        let username = "";
        username = firstName.trim() + lastName.trim().charAt(0) + midName.trim().charAt(0);
        return username;
    }

    public convertToDate(dateVN: string): string {
        return dateVN.substring(3, 5) + "/" + dateVN.substring(0, 2) + "/" + dateVN.substring(6, dateVN.length);
    }

    public addErrorSqlToListErrorServer(listErrorServer: ErrorValidateServer[], listErrorSql: ErrorSqlServer[]): ErrorValidateServer[] {
        listErrorSql.forEach(errSQL => {
            const indexERR: number = listErrorServer.findIndex(err => err.no === errSQL.no);
            if (indexERR !== -1) {
                listErrorServer[indexERR].content += "; Error SQL: " + errSQL.content;
            } else {
                const errValidateServer: ErrorValidateServer = new ErrorValidateServer();
                errValidateServer.no = errSQL.no;
                errValidateServer.content = "Error SQL: " + errSQL.content;
                listErrorServer.push(errValidateServer);
            }
        });
        return listErrorServer;
    }

  
}