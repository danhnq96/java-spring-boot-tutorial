import { Injectable } from '@angular/core';
import * as FileSaver from 'file-saver';
import * as XLSX from 'xlsx';

const EXCEL_TYPE = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8';
const EXCEL_EXTENSION = '.xlsx';

@Injectable()
export class ExcelService {

    constructor() { }

    public exportAsExcelFile(json: any[], excelFileName: string): void {
        const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(json);
        const workbook: XLSX.WorkBook = { Sheets: { 'data': worksheet }, SheetNames: ['data'] };
        const excelBuffer: any = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' });
        // const excelBuffer: any = XLSX.write(workbook, { bookType: 'xlsx', type: 'buffer' });
        this.saveAsExcelFile(excelBuffer, excelFileName);
    }

    private saveAsExcelFile(buffer: any, fileName: string): void {
        const data: Blob = new Blob([buffer], {
            type: EXCEL_TYPE
        });
        FileSaver.saveAs(data, fileName + '_export_' + new Date().getTime() + EXCEL_EXTENSION);
    }

    // Read File Excel
    public readFileExcel(file: any, event: any): Promise<any> {
        let arrayBuffer: any;
        file = event.target.files[0];
        const fileReader: FileReader = new FileReader();
        fileReader.readAsArrayBuffer(file);
        return new Promise((resolve) => {
            fileReader.onload = () => {
                arrayBuffer = fileReader.result;
                const data = new Uint8Array(arrayBuffer);
                const arr = [];
                for (let i: number = 0; i !== data.length; ++i) arr[i] = String.fromCharCode(data[i]);
                const bstr = arr.join("");
                const workbook = XLSX.read(bstr, { type: "binary" });
                const firstSheetName = workbook.SheetNames[0];
                const worksheet = workbook.Sheets[firstSheetName];
                resolve(XLSX.utils.sheet_to_json<any>(worksheet, { raw: true }));
            }
        });
    }
}