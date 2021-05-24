import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatRippleModule } from '@angular/material/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatTabsModule } from '@angular/material/tabs';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatToolbarModule } from '@angular/material/toolbar';
import { FuseSharedModule } from '@fuse/shared.module';
import { ChatPanelComponent } from 'app/layout/components/chat-panel/chat-panel.component';
import { ChatPanelService } from 'app/layout/components/chat-panel/chat-panel.service';
import { MatMenuModule } from '@angular/material/menu';
@NgModule({
    declarations: [
        ChatPanelComponent
    ],
    providers: [
        ChatPanelService
    ],
    imports: [
        MatMenuModule,
        MatButtonModule,
        MatFormFieldModule,
        MatIconModule,
        MatInputModule,
        MatTabsModule,
        MatTooltipModule,
        MatRippleModule,
        MatToolbarModule,
        FuseSharedModule
    ],
    exports: [
        ChatPanelComponent
    ]
})
export class ChatPanelModule {
}
