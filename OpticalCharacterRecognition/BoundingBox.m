function [Features, Labels] = BoundingBox(L, im2, label, locations, classes)

Nc=max(max(L));

[numRows, numCols] = size(im2);
figure
imagesc(L)
hold on;
Features = [];
Labels = [];

for i=1:Nc;
 [r,c]=find(L==i);
 maxr=max(r);
 minr=min(r);
 maxc=max(c);
 minc=min(c);
 rectangle('Position',[minc,minr,maxc-minc+1,maxr-minr+1], 'EdgeColor','w');
 
 if(minr-1<1)
     minr=2;
 end
 if(maxr+1>numCols)
     maxr = numCols-1;
 end
 if(minc-1<1)
     minc=2;
 end
 if(maxc+1>numCols)
     maxc = numCols-1;
 end
 
 cim=im2(minr-1:maxr+1,minc-1:maxc+1);
 [centroid, theta, roundness, inmo] = moments(cim, 1);
 Features = [Features; theta, roundness, inmo];
 Labels = [Labels; label];
end
hold off