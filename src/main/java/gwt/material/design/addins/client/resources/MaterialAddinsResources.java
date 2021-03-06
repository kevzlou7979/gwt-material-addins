package gwt.material.design.addins.client.resources;

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

public interface MaterialAddinsResources extends ClientBundle {
    MaterialAddinsResources INSTANCE = GWT.create(MaterialAddinsResources.class);

    @Source("js/timepicker.min.js")
    TextResource timepickerJs();

    @Source("js/stickyheaders.min.js")
    TextResource subHeaderJs();

    @Source("js/cta.min.js")
    TextResource pathAnimatorJs();

    @Source("js/waterfall.min.js")
    TextResource waterfallJs();

    @Source("js/scrollfire.min.js")
    TextResource scrollFireJs();

    @Source("js/bubble.min.js")
    TextResource bubbleJs();

    @Source("js/interact.min.js")
    TextResource interactJs();

    @Source("js/masonry.min.js")
    TextResource masonryJs();

    @Source("js/tinymce.min.js")
    TextResource tinymceJs();

}
