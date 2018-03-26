/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
import UIKit

@objc(SlideShowLayout)
open class SlideShowLayout: UICollectionViewFlowLayout {

	let DistanceBetweenCenters = CGFloat(250)
	let ZoomFactor = CGFloat(0.3)
	let TranslateFactor = CGFloat(100)

	internal var visibleRect: CGRect {
		let visibleRectOrigin = self.collectionView!.contentOffset
		let visibleRectSize = self.collectionView!.bounds.size

		return CGRect(origin: visibleRectOrigin, size: visibleRectSize)
	}

	override init() {
		super.init()
		initialize()
	}

	required public init?(coder aDecoder: NSCoder) {
		super.init(coder: aDecoder)
		initialize()
	}

	open override func layoutAttributesForElements(
			in rect: CGRect) -> [UICollectionViewLayoutAttributes]? {

		guard let attributesArray = super.layoutAttributesForElements(in: rect)
		else {
			return []
		}

		let attributesCopy = attributesArray.map { $0.copy() as! UICollectionViewLayoutAttributes }

		for attributes in attributesCopy {
			if attributes.frame.intersects(rect) {
				setAttributes(attributes: attributes, visibleRect: visibleRect)
			}
		}

		return attributesCopy
	}

	open override func shouldInvalidateLayout(forBoundsChange newBounds: CGRect) -> Bool {
		return true
	}

	open override func layoutAttributesForItem(
			at indexPath: IndexPath) -> UICollectionViewLayoutAttributes? {

		guard let attributes = super.layoutAttributesForItem(at: indexPath)
		else {
			return nil
		}

		let attributesCopy = attributes.copy() as! UICollectionViewLayoutAttributes

		setAttributes(attributes: attributesCopy, visibleRect: visibleRect)

		return attributesCopy
	}

	open override func targetContentOffset(
			forProposedContentOffset proposedContentOffset: CGPoint,
			withScrollingVelocity velocity: CGPoint) -> CGPoint {

		var offsetAdjustment = CGFloat(MAXFLOAT)
		let horizontalCenter = proposedContentOffset.x + collectionView!.bounds.width / 2

		let targetRect = CGRect(
				x: proposedContentOffset.x,
				y: 0,
				width: collectionView!.bounds.width,
				height:  collectionView!.bounds.width)

		let attributes = super.layoutAttributesForElements(in: targetRect)

		// Look for the closest image to the center
		for attribute in attributes! {
			let itemHorizontalCenter = attribute.center.x

			if abs(itemHorizontalCenter - horizontalCenter) < abs(offsetAdjustment) {

				offsetAdjustment = itemHorizontalCenter - horizontalCenter
			}
		}

		return CGPoint(x: proposedContentOffset.x + offsetAdjustment, y: proposedContentOffset.y)
	}

	internal func initialize() {
		itemSize = CGSize(width: 200, height: 200)
		scrollDirection = .horizontal
		minimumLineSpacing = 50
		minimumInteritemSpacing = 1000
	}

	internal func setAttributes(attributes: UICollectionViewLayoutAttributes, visibleRect: CGRect) {
		let distanceToCenter = visibleRect.midX - attributes.center.x
		let normalizedDistance = distanceToCenter / DistanceBetweenCenters

		if abs(distanceToCenter) < DistanceBetweenCenters {
			// rangeValue 1-0, 1 means in the center of the screen
			let rangeValue = 1 - abs(normalizedDistance)

			let zoom = 1 + ZoomFactor * rangeValue
			let translate = ((TranslateFactor * rangeValue) - 50) * -1
			let alpha = rangeValue + 0.5

			let scaleTransform = CATransform3DMakeScale(zoom, zoom, 1.0)
			let translateTransform = CATransform3DMakeTranslation(0, translate, 0)

			attributes.transform3D = CATransform3DConcat(scaleTransform, translateTransform)
			attributes.alpha = alpha
			attributes.zIndex = 1
		}
		else {
			let frame = attributes.frame
			let newFrame = CGRect(origin: CGPoint(x:frame.origin.x, y:frame.origin.y + 50), size: frame.size)

			attributes.alpha = 0.5
			attributes.frame = newFrame
			attributes.transform3D = CATransform3DIdentity
			attributes.zIndex = 0
		}

	}

}
