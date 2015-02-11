Android Custom View
===================

This is a demo of custom view to slide cancel

----------
Demo
-------------

Demo Pic

![image](https://github.com/nuptboyzhb/DownloadProgressViewDemo/blob/master/demo/demo.png)


----------
Usage

```
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		downloadProgressView1 = (DownloadProgressView) findViewById(R.id.wpv_download_1);
		downloadProgressView1.setMax(100);
		downloadProgressView1.setMaxFileLenght(20);

		downloadProgressView2 = (DownloadProgressView) findViewById(R.id.wpv_download_2);
		downloadProgressView2.setMax(100);
		downloadProgressView2.setMaxFileLenght(8);
		
		downloadProgressView3 = (DownloadProgressView) findViewById(R.id.wpv_download_3);
		downloadProgressView3.setMax(100);
		downloadProgressView3.setMaxFileLenght(19);

		new Thread(this).start();
	}

	@Override
	public void run() {

		for (int i = 0; i <= 100; i++) {
			downloadProgressView1.setProgress(i);
			downloadProgressView2.setProgress(i);
			downloadProgressView3.setProgress(i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
```


License
-------------

Copyright 2014  [Zheng Haibo](https://github.com/nuptboyzhb/)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
