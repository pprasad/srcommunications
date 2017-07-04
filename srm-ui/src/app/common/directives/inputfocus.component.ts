import {Directive, Renderer, ElementRef, OnInit, AfterViewInit, Input} from '@angular/core';
@Directive({
    selector:'[focusInput]'
})
export  class FocusInputDirective implements OnInit, AfterViewInit{
    @Input("focusInput") priority:number=0;

    static instances: FocusInputDirective[] = [];

    constructor(public renderer: Renderer, public elementRef: ElementRef){ }   
    ngOnInit(): void {
     FocusInputDirective.instances.push(this)
   }
   ngAfterViewInit(): void {
    setTimeout(() => {
      FocusInputDirective.instances.splice(FocusInputDirective.instances.indexOf(this), 1);
    });
    if(FocusInputDirective.instances.every((i) => this.priority >= i.priority)) {
       this.renderer.invokeElementMethod(
          this.elementRef.nativeElement, 'focus', []);
      }
  }
}